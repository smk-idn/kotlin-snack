package idn.smk.kotlinsnack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv_order_result, tv_order_amount;
    Button btn_order, btn_increase_order_amount, btn_decrease_order_amount;
    EditText et_customer_name;
    RadioGroup rg_choose_snacks;
    RadioButton rb_snack;

    private int orderAmount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        tv_order_amount.setText(String.valueOf(orderAmount));

        btn_increase_order_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseOrder();
            }
        });

        btn_decrease_order_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseOrder();
            }
        });

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String customerName = et_customer_name.getText().toString();

                int chooseSnacksSelectedID = rg_choose_snacks.getCheckedRadioButtonId();
                rb_snack = findViewById(chooseSnacksSelectedID);

                String snackName = rb_snack.getText().toString();

                if (customerName.isEmpty()) {
                    toast(getString(R.string.customer_not_empty));
                } else if (snackName.isEmpty()) {
                    toast(getString(R.string.snacks_not_empty));
                } else {
                    if (snackName.equals(getString(R.string.cireng_isi))) {
                        showOrderResult(customerName, snackName, 1000);
                    } else if (snackName.equals(getString(R.string.bakso_goreng))) {
                        showOrderResult(customerName, snackName, 5000);
                    } else if (snackName.equals(getString(R.string.roti_bakar))) {
                        showOrderResult(customerName, snackName, 2000);
                    } else if (snackName.equals(getString(R.string.seblak))) {
                        showOrderResult(customerName, snackName, 5000);
                    }
                }
            }
        });
    }

    private void initView() {
        tv_order_result = findViewById(R.id.tv_order_result);
        tv_order_amount = findViewById(R.id.tv_order_amount);
        btn_order = findViewById(R.id.btn_order);
        btn_increase_order_amount = findViewById(R.id.btn_increase_order_amount);
        btn_decrease_order_amount = findViewById(R.id.btn_decrease_order_amount);
        et_customer_name = findViewById(R.id.et_customer_name);
        rg_choose_snacks = findViewById(R.id.rg_choose_snacks);
    }

    private void increaseOrder() {
        if (orderAmount >= 100) {
            toast(getString(R.string.max_order));
        } else {
            orderAmount += 1;
            tv_order_amount.setText(String.valueOf(orderAmount));
        }
    }

    private void decreaseOrder() {
        if (orderAmount < 1) {
            toast(getString(R.string.min_order));
        } else {
            orderAmount -= 1;
            tv_order_amount.setText(String.valueOf(orderAmount));
        }
    }

    private void showOrderResult(String customerName, String snackName, int price) {
        Locale localeID = new Locale("in", "ID");

        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(localeID);

        tv_order_result.setText(("Nama Pemesan : " + customerName + "\n" + "Jenis Jajanan :" +
                snackName + "\n" + "Total Pesanan : " + orderAmount + "\n" + "Total Harga : " +
                rupiahFormat.format((double) price * orderAmount)));
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
