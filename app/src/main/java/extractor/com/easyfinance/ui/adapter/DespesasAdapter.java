package extractor.com.easyfinance.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import extractor.com.easyfinance.controler.EasyFinance;
import extractor.com.easyfinance.model.entities.Despesa;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class DespesasAdapter extends BaseAdapter{

    private ArrayList<Despesa> despesas;
    private Context context;

    public DespesasAdapter(ArrayList<Despesa> despesas) {
        this.despesas = despesas;
        context = EasyFinance.getAppContext();
    }

    @Override
    public int getCount() {
        return despesas.size();
    }

    @Override
    public Despesa getItem(int position) {
        return despesas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return new Long(despesas.get(position).getID());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.item_listview, parent, false);

        TextView txtDescricao = (TextView) rootView.findViewById(R.id.txtDescricao);
        TextView txtData = (TextView) rootView.findViewById(R.id.txtData);
        TextView txtValor = (TextView) rootView.findViewById(R.id.txtValor);

        txtDescricao.setText(getItem(position).getDescricao());
        txtData.setText(getItem(position).getData());
        txtValor.setText(getItem(position).getValor().toString());

        return rootView;
    }
}
