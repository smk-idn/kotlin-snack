package idn.smk.kotlinsnack

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var orderAmount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_order_amount.text = orderAmount.toString()

        btn_increase_order_amount.setOnClickListener { increaseOrder() }

        btn_decrease_order_amount.setOnClickListener { decreaseOrder() }

        btn_order.setOnClickListener {
            val customerName = et_customer_name.text.toString()

            val chooseSnacksSelectedID = rg_choose_snacks.checkedRadioButtonId
            val rbSnack = findViewById<RadioButton>(chooseSnacksSelectedID)

            val snackName = rbSnack.text.toString()

            if (customerName.isEmpty()) {
                toast(getString(R.string.customer_not_empty))
            } else if (snackName.isEmpty()) {
                toast(getString(R.string.snacks_not_empty))
            } else {
                if (snackName == getString(R.string.cireng_isi)) {
                    showOrderResult(customerName, snackName, 1000)
                } else if (snackName == getString(R.string.bakso_goreng)) {
                    showOrderResult(customerName, snackName, 5000)
                } else if (snackName == getString(R.string.roti_bakar)) {
                    showOrderResult(customerName, snackName, 2000)
                } else if (snackName == getString(R.string.seblak)) {
                    showOrderResult(customerName, snackName, 5000)
                }
            }
        }
    }

    private fun increaseOrder() {
        if (orderAmount >= 100) {
            toast(getString(R.string.max_order))
        } else {
            orderAmount += 1
            tv_order_amount.text = orderAmount.toString()
        }
    }

    private fun decreaseOrder() {
        if (orderAmount < 1) {
            toast(getString(R.string.min_order))
        } else {
            orderAmount -= 1
            tv_order_amount.text = orderAmount.toString()
        }
    }

    private fun showOrderResult(customerName: String, snackName: String, price: Int) {
        val localeID = Locale("in", "ID")

        val rupiahFormat = NumberFormat.getCurrencyInstance(localeID)

        tv_order_result.text = "Nama Pemesan : " + customerName + "\n" + "Jenis Jajanan :" +
                snackName + "\n" + "Total Pesanan : " + orderAmount + "\n" + "Total Harga : " +
                rupiahFormat.format(price.toDouble() * orderAmount)
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
