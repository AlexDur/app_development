package de.sanexio.kotlin.mypresents


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.fragment_neuer_eintrag.*


/**
 * A simple [Fragment] subclass.
 * Use the [NeuerEintragFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NeuerEintragFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var radiogruppe : RadioGroup? = null
    private var editName : EditText? = null
    private var editShop : EditText? = null
    private var editFuer : EditText? = null
    private var bildId : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater?.inflate(R.layout.fragment_neuer_eintrag,container,false)
        radiogruppe = mView?.findViewById(R.id.bildWahlGruppe)

        editFuer = mView?.findViewById(R.id.editTextFuer)
        editName = mView?.findViewById(R.id.editTextName)
        editShop = mView?.findViewById(R.id.editTextShop)

        val buttonSave = mView?.findViewById<Button>(R.id.buttonSave)
        buttonSave?.setOnClickListener {
            speichern()
        }
    return mView
    }

override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    radiogruppe?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ group, checkedId ->

        when(checkedId){
            R.id.radioButtonEinrichtung -> {
               bildId = context.resources.getIdentifier("einrichtung", "drawable",context.getPackageName())
                Log.i("test", "geklicktWurde Einrichtung $bildId ")

            }
            R.id.radioButtonEssen -> {
                bildId = context.resources.getIdentifier("essen", "drawable",context.getPackageName())

                Log.i("test", "geklicktWurde essen $bildId")
            }
            R.id.radioButtonGiftCard -> {
                bildId = context.resources.getIdentifier("giftcard", "drawable",context.getPackageName())

                Log.i("test", "geklicktWurde Card $bildId")
            }
            R.id.radioButttonGame -> {
                bildId = context.resources.getIdentifier("spiel", "drawable",context.getPackageName())
                Log.i("test", "geklicktWurde Game $bildId")
            }
            R.id.radioButtonSuesses -> {
                bildId = context.resources.getIdentifier("suessigkeiten", "drawable",context.getPackageName())
                Log.i("test", "geklicktWurde Süsses $bildId")
            }
            R.id.radioButtonTechnik -> {
                bildId = context.resources.getIdentifier("technik", "drawable",context.getPackageName())
                Log.i("test", "geklicktWurde Technik $bildId")
            }
        }

    })




}
    fun speichern(){
        val name = editName?.text.toString()
        val fuer = editFuer?.text.toString()
        val shop = editShop?.text.toString()
        Log.i("test", "BildId in Speichern:  $bildId")

        if(bildId!=0 && !name.isEmpty() && !fuer.isEmpty() && !shop.isEmpty()){
            val neuenDatensatz = GiftObject(0,name,0,shop,"",bildId,fuer)
            val dataId = GiftTableHelper(context).speichereNeuenEintrag(neuenDatensatz)
            val sss ="Gespeichert! ID: $dataId"
            Toast.makeText(context,sss, Toast.LENGTH_LONG).show()
            oeffneUebersicht()
        }else Toast.makeText(context,getString(R.string.msgBildWahl), Toast.LENGTH_LONG).show()
    }
    fun oeffneUebersicht(){
        val fragment = UebersichtGeschenkeFragment()
        this.fragmentManager.beginTransaction()
                .replace(R.id.contentArea,fragment)
                .addToBackStack(null)
                .commit()
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
* @return A new instance of fragment NeuerEintragFragment.
*/
// TODO: Rename and change types and number of parameters
fun newInstance(param1: String, param2: String): NeuerEintragFragment {
   val fragment = NeuerEintragFragment()
   val args = Bundle()
   args.putString(ARG_PARAM1, param1)
   args.putString(ARG_PARAM2, param2)
   fragment.arguments = args
   return fragment
}
}

}// Required empty public constructor
