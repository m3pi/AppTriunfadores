package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import imerosa.apptriunfadores.R;
import models.SolicitudPrestamo;

/**
 * Created by mompi3p on 10/12/2015.
 */
public class SolicitudPrestamoAdapter extends ArrayAdapter<SolicitudPrestamo> {

    private  int resource;

    public SolicitudPrestamoAdapter(Context context, int resource, List<SolicitudPrestamo> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(resource, parent, false);

        SolicitudPrestamo solicitudPrestamo = getItem(position);

        ((TextView)convertView.findViewById(R.id.txtMonto)).setText(solicitudPrestamo.Cantidad);
        ((TextView)convertView.findViewById(R.id.txtFechaSolicitada)).setText(solicitudPrestamo.FechSolicitud);
        ((TextView)convertView.findViewById(R.id.txtComentario)).setText(solicitudPrestamo.Comentario);
        ((TextView)convertView.findViewById(R.id.txtInteres)).setText(solicitudPrestamo.TasaInteres);
        ((TextView)convertView.findViewById(R.id.txtTipoPago)).setText(solicitudPrestamo.TipoPago);
        ((TextView)convertView.findViewById(R.id.txtIdCliente)).setText(solicitudPrestamo.IdCliente);

        return convertView;
    }

}
