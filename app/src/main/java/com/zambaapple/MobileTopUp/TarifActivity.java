package com.zambaapple.MobileTopUp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zambaapple.MobileTopUp.MOdels.Tarif;
import com.zambaapple.MobileTopUp.OperatorByPhoneIso.Fx;
import com.zambaapple.MobileTopUp.OperatorByPhoneIso.OperatorValue;
import com.zambaapple.MobileTopUp.Sdk.APIClient;
import com.zambaapple.MobileTopUp.Sdk.createtoken;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TarifActivity extends AppCompatActivity {
    createtoken create = APIClient.getClient().create(createtoken.class);
    RecyclerView recyclerView;
    ArrayList<Tarif> tarifs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif);
        View view = getLayoutInflater().inflate(R.layout.recycler, null, false);
         recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(TarifActivity.this));
        tarifs = new ArrayList<>();
        String phonetically=getIntent().getStringExtra("PhoneCde")+getIntent().getStringExtra("Phonetically");
        String countries=getIntent().getStringExtra("countries");
        String country=getIntent().getStringExtra("countriesName");
      String iso= getIntent().getStringExtra("countries");
      String operationally= getIntent().getStringExtra("operator");
     getIntent().getStringExtra("commission");
       getIntent().getStringExtra("discount");
        System.out.println(iso+operationally);
      getPhoneCallsOperatorDatas(phonetically,country,countries);
    }
    public class TariffsAdapter extends RecyclerView.Adapter<TariffsAdapter.Holder> {
        ArrayList<Tarif> tarifs;
        Integer oratorio;
        String countries;

        public TariffsAdapter(ArrayList<Tarif> tariffs, Integer operators, String countries) {
            this.tarifs = tariffs;
            this.oratorio = operators;
            this.countries = countries;
        }

        @NonNull
        @NotNull
        @Override
        public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view=getLayoutInflater().inflate(R.layout.payxml,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull Holder holder, @SuppressLint("RecyclerView") int position) {
            holder.currency.setText(tarifs.get(position).getCurenncy());
            holder.tarifrecurrent.setText(tarifs.get(position).getTarif());
            double num1=Double.parseDouble(tarifs.get(position).getTarif());
            double num2=Double.parseDouble(tarifs.get(position).getRate());
            double result=num1*num2;
            String pattern = "###.##";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);

            String format = decimalFormat.format(result);
            System.out.println(format);

            holder.receivamount.setText(String.valueOf(format));
            holder.currencyreceiv.setText(tarifs.get(position).getReceivcurrency());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(TarifActivity.this,PayActivity.class);
                    intent.putExtra("amounted",tarifs.get(position).getTarif());
                    intent.putExtra("currency",String.valueOf(tarifs.get(position).getCurenncy()).toLowerCase());
                    intent.putExtra("linenumber",getIntent().getStringExtra("PhoneCde")+getIntent().getStringExtra("Phonetically"));
                    intent.putExtra("countryside",countries);
                    intent.putExtra("countrymen",getIntent().getStringExtra("countriesName"));
                    intent.putExtra("operator",String.valueOf(oratorio));
                    intent.putExtra("commission", getIntent().getStringExtra("commission"));
                    intent.putExtra("discount", getIntent().getStringExtra("discount"));
                    intent.putExtra("currencysymbol",getIntent().getStringExtra("currencysymbol"));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return tarifs.size();
        }


        class Holder extends RecyclerView.ViewHolder {
            TextView currency, tarifrecurrent,currencyreceiv,receivamount;

            public Holder(@NonNull @NotNull View itemView) {
                super(itemView);
                tarifrecurrent = itemView.findViewById(R.id.tariftextview);
                currency = itemView.findViewById(R.id.currencytextview);
                currencyreceiv = itemView.findViewById(R.id.receivcurrencytextview);
                receivamount = itemView.findViewById(R.id.receivtariftextview);

            }
        }
    }
    private void getPhoneCallsOperatorDatas(String phonenumber, String Countrie, String CountrieIso) {
        System.out.println(">>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<!!!!!!!!!"+CountrieIso);
        Call<OperatorValue> call = create.getCountrieOperator(phonenumber, Countrie, CountrieIso);
        call.enqueue(new Callback<OperatorValue>() {
            @Override
            public void onResponse(Call<OperatorValue> call, Response<OperatorValue> response) {
                OperatorValue root = response.body();
                Fx fx=root.getFx();

                if (root.getTimeStamp() !=null){
                    System.out.println(root.getTimeStamp()+"\n"+root.getErrorCode()+"\n"+root.getMessage());
                }
                System.out.println(root.getOperatorId());
                if (root.getSuggestedAmounts().size()>0 || root.getFixedAmounts().size()<0){
                    for (int i = 0; i < root.getSuggestedAmounts().size(); i++) {
                        System.out.println("Suggestions amounts"+root.getSuggestedAmounts().get(i));
                        tarifs.add(new Tarif(root.getSuggestedAmounts().get(i).toString(),
                                root.getSenderCurrencyCode(),
                                String.valueOf(fx.getRate()),
                                root.getDestinationCurrencyCode()));

                        System.out.println(root.getSuggestedAmounts().get(i));
                    }
                }else if (root.getFixedAmounts().size()>0 || root.getSuggestedAmounts().size()<0){
                    for (int i = 0; i < root.getFixedAmounts().size(); i++) {
                        System.out.println("Suggestions amounts"+root.getFixedAmounts().get(i));
                        tarifs.add(new Tarif(root.getFixedAmounts().get(i).toString(),
                                root.getSenderCurrencyCode(),
                                String.valueOf(fx.getRate()),
                                root.getDestinationCurrencyCode()));

                        System.out.println(root.getFixedAmounts().get(i));
                    }
                }


                TariffsAdapter adapter = new TariffsAdapter(tarifs,root.getOperatorId(),CountrieIso);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<OperatorValue> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}