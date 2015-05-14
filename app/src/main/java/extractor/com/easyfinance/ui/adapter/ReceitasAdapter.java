package extractor.com.easyfinance.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

import extractor.com.easyfinance.controler.EasyFinance;
import extractor.com.easyfinance.model.entities.Receita;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class ReceitasAdapter extends BaseAdapter {

    private ArrayList<Receita> receitas;
    private Context context;
    private NumberFormat numberFormat;

    public ReceitasAdapter(ArrayList<Receita> receitas) {
        this.receitas = receitas;
        context = EasyFinance.getAppContext();
        numberFormat = NumberFormat.getCurrencyInstance();
    }

    @Override
    public int getCount() {
        return receitas.size();
    }

    @Override
    public Receita getItem(int position) {
        return receitas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return new Long(receitas.get(position).getID());
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
        txtValor.setText(numberFormat.format(getItem(position).getValor()));

        return rootView;
    }
}
