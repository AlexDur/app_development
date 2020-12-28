package de.sanexio.kotlin.mypresents

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val mManager = supportFragmentManager
    val shoppingFragment = ShoppingFragment()
    val uebersichtGeschenkeFragment = UebersichtGeschenkeFragment()
    val neuerEintragFragment = NeuerEintragFragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_giftlist -> {

                val mTransaction = mManager.beginTransaction()
                mTransaction.replace(R.id.contentArea, uebersichtGeschenkeFragment)
                mTransaction.addToBackStack(null)
                mTransaction.commit()


                //return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_shoppinglist -> {
                val mTransaction = mManager.beginTransaction()
                mTransaction.addToBackStack(null)
                mTransaction.replace(R.id.contentArea, shoppingFragment)
                mTransaction.commit()
               // return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_newEntry -> {
                val mTransaction = mManager.beginTransaction()
                mTransaction.addToBackStack(null)
                mTransaction.replace(R.id.contentArea, neuerEintragFragment)
                mTransaction.commit()
              //  return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
       // GiftTableHelper(this).loescheDatensatz(5)
        //GiftTableHelper(this).alleEintraege()
       // GiftTableHelper(this).setGekauftTrue(2)


    }
}
