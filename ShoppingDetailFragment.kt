package de.sanexio.kotlin.mypresents


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 * Use the [ShoppingDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoppingDetailFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var id: String? = null
    private var idInt: Int? = null
    private var mParam2: String? = null
    var textViewName : TextView? = null
    var textViewFuer : TextView? = null
    var textViewBeschreibung : TextView? = null
    var textViewGekauft : TextView? = null
    var buttonGekauft : Button? = null
    var buttonLoeschen : Button? = null
    var bild : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            id = arguments.getString("id")
            Log.i("test", "ID Empfangen: $id")
           // mParam2 = arguments.getString(ARG_PARAM2)
            idInt = Integer.parseInt(id)
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater?.inflate(R.layout.fragment_shopping_detail, container,false)

        textViewName = mView?.findViewById(R.id.textViewNameDetailSeite)
        textViewBeschreibung = mView?.findViewById(R.id.textViewBeschreibungDetailseite)
        textViewFuer = mView?.findViewById(R.id.textViewFuerDetailseite)
        textViewGekauft = mView?.findViewById(R.id.textViewGekauft)
        bild = mView?.findViewById(R.id.imageViewDetailseite)
        buttonGekauft = mView?.findViewById(R.id.BtnGekauft)
        buttonGekauft?.setOnClickListener {
            GiftTableHelper(context).setGekauftTrue(idInt!!)
            Log.i("test", "gekauft auf True!")
        }
        buttonLoeschen = mView?.findViewById(R.id.buttonLoeschen)
        buttonLoeschen?.setOnClickListener {
            GiftTableHelper(context).loescheDatensatz(idInt!!)
            Log.i("test", "GELÖSCHT")
        }

        return mView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datensatz = GiftTableHelper(context).ausgeswelterDatensatz(idInt!!)
        textViewName?.text = datensatz.name
        textViewBeschreibung?.text =""
        textViewFuer?.text = datensatz.geschenkFuer
        bild?.setImageResource(datensatz.bild)
        if (datensatz.gekauft == 1) textViewGekauft?.text = "Gekauft" else textViewGekauft?.text = "Nicht gekauft"


    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoppingDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): ShoppingDetailFragment {
            val fragment = ShoppingDetailFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
