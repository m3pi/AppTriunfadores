package imerosa.apptriunfadores;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapters.SolicitudPrestamoAdapter;
import models.SolicitudPrestamo;
import utils.ConstantsUtils;
import utils.SolicitudPrestamoUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListSolicitudesPrestamosFragment extends Fragment {

    private ListView listView;

    public ListSolicitudesPrestamosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_solicitudes_prestamos, container, false);
        View view = inflater.inflate(R.layout.fragment_list_solicitudes_prestamos, container, false);

//        List<SolicitudPrestamo> solicitudesPrestamos = getSolicitudesPrestamos();
//        SolicitudPrestamoAdapter adapter = new SolicitudPrestamoAdapter(getContext(), R.layout.simple_list_item_solicitud_prestamo_layout, solicitudesPrestamos);
//
//        ((ListView)view.findViewById(R.id.lv_solicitudes_prestamos).setAdapter(adapter));



        listView = (ListView)view.findViewById(R.id.lv_solicitudes_prestamos);

        new SolicitudesPrestamoTask().execute();

        return view;
    }


    private void updateListView(ArrayList<SolicitudPrestamo> solicitudPrestamos){

        listView.setAdapter(new SolicitudPrestamoAdapter(
                getContext(),R.layout.simple_list_item_solicitud_prestamo_layout,
                solicitudPrestamos));
    }

    private class SolicitudesPrestamoTask extends AsyncTask<Object, Void, ArrayList<SolicitudPrestamo>>{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getResources().getString(R.string.lbl_solicitudes_search_loader));
            progressDialog.show();
        }

        @Override
        protected ArrayList<SolicitudPrestamo> doInBackground(Object... params) {

            List<SolicitudPrestamo> solicitudPrestamos = new ArrayList<>();

            try {
                String solicitudes = SolicitudPrestamoUtils.
                        getTriunfadoresForSearchTerm(ConstantsUtils.TRIUNFADORES_SOLICITUDES_PRESTAMOS);
                //JSONArray jsonResponse = new JSONArray(solicitudes);

                JSONArray jsonArray = new JSONArray(solicitudes);
                JSONObject  jsonObject;

                for (int i = 0; i<jsonArray.length(); i++){
                    SolicitudPrestamo solicitudPre = new SolicitudPrestamo();
                    jsonObject =jsonArray.getJSONObject(i);


                    solicitudPre.Cantidad=jsonObject.getString("Cantidad");
                    solicitudPre.Comentario=jsonObject.getString("Comentario");
                    solicitudPre.FechSolicitud=jsonObject.getString("FechSolicitud");
                    solicitudPre.TasaInteres=jsonObject.getString("TasaInteres");
                    solicitudPre.TipoPago=jsonObject.getString("TipoPago");

                    solicitudPrestamos.add(i, solicitudPre);
                }

            }catch (Exception e){
                Log.e("JSON error: ", Log.getStackTraceString(e));
            }
            return (ArrayList)solicitudPrestamos;
        }

        @Override
        protected void onPostExecute(ArrayList<SolicitudPrestamo> solicitudPrestamos) {
            progressDialog.dismiss();

            if (solicitudPrestamos.isEmpty()) {
                Toast.makeText(getContext(), getResources().getString(R.string.lbl_solicitudes_not_found),
                        Toast.LENGTH_SHORT).show();
            } else {
                updateListView(solicitudPrestamos);
                Toast.makeText(getContext(), getResources().getString(R.string.lbl_solicitudes_downloaded),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }



}
