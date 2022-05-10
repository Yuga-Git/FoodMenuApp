package com.example.khadyayatra.Fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.khadyayatra.Adapter.DashboardRecyclerAdapter
import com.example.khadyayatra.R
import com.example.khadyayatra.model.Restaurants
import com.example.khadyayatra.util.ConnectionManager
import org.json.JSONObject
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerViewDashboard: RecyclerView
    lateinit var layoutManagerDashboard: RecyclerView.LayoutManager
    lateinit var recyclerAdapter : DashboardRecyclerAdapter
    lateinit var btnCheckInternet : Button
    val restInfoList = arrayListOf<Restaurants>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        // Inflate the layout for this fragment

        recyclerViewDashboard = view.findViewById(R.id.recyclerviewDashboard)
        layoutManagerDashboard = LinearLayoutManager(activity)


        //fetching data from internet using volley
        val queue = Volley.newRequestQueue(activity as Context)
        val url = "http://13.235.250.119/v2/restaurants/fetch_result/"
        val jsonObjectRequest = object : JsonObjectRequest(Method.GET, url, null, Response.Listener { it ->



            val data = it.getJSONObject("data")
            val success = data.getBoolean("success")
            try {


                if (success) {
                    val dataArray = data.getJSONArray("data")
                    for (i in 0 until dataArray.length()) {
                        val restJsonObject = dataArray.getJSONObject(i)
                        val restObject = Restaurants(
                            restJsonObject.getString("id"),
                            restJsonObject.getString("name"),
                            restJsonObject.getString("rating"),
                            restJsonObject.getString("cost_for_one"),
                            restJsonObject.getString("image_url")
                        )
                        restInfoList.add(restObject)
                        recyclerAdapter =
                            DashboardRecyclerAdapter(activity as Context, restInfoList)
                        recyclerViewDashboard.adapter = recyclerAdapter
                        recyclerViewDashboard.layoutManager = layoutManagerDashboard

                        /*recyclerViewDashboard.addItemDecoration(DividerItemDecoration(recyclerViewDashboard.context,
                        (layoutManagerDashboard as LinearLayoutManager).orientation))*/
                    }
                } else {
                    Toast.makeText(activity as Context,
                        "Some error occurred!!!",
                        Toast.LENGTH_SHORT).show()
                }
            }catch (e : Exception){

            }
        }, Response.ErrorListener {


        }){
            override fun getHeaders():MutableMap<String, String>{
                val headers = HashMap<String, String>()
                headers["Content-Type"]="application/json"
                headers["token"] = "9bf534118365f1"
                return headers
            }
        }

        queue.add(jsonObjectRequest)
       //end of volley code

        //code for checking the internet connection
        btnCheckInternet = view.findViewById(R.id.btnCheckInternet)

        btnCheckInternet.setOnClickListener{
            if(ConnectionManager().checkConnectivity(activity as Context)){
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success")
                dialog.setMessage("Internet connection found")
                dialog.setPositiveButton("Ok"){text, listener ->

                }
                dialog.setPositiveButton("Cancel"){text, listener ->

                }
                dialog.create()
                dialog.show()
            }else{
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Error")
                dialog.setMessage("Internet connection is not found")
                dialog.setPositiveButton("Ok"){text, listener ->

                }
                dialog.setPositiveButton("Cancel"){text, listener ->

                }
                dialog.create()
                dialog.show()
            }
        }
        //End of code to check the internet connection


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}