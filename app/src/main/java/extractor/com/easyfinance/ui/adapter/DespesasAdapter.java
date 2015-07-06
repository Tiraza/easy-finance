package extractor.com.easyfinance.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;

import extractor.com.easyfinance.model.entities.Receita;
import extractor.com.easyfinance.util.UtilDate;
import extractor.com.myapplication.R;
import io.realm.Realm;
import io.realm.RealmResults;

public class DespesasAdapter extends RecyclerView.Adapter<DespesasAdapter.ViewHolderDespesas> {

    private RealmResults<Receita> listDespesas;
    private NumberFormat numberFormat;

    public DespesasAdapter(Context context) {
        listDespesas = Realm.getInstance(context).where(Receita.class).findAll();
        numberFormat = NumberFormat.getCurrencyInstance();
    }

    @Override
    public ViewHolderDespesas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, parent, false);
        return new ViewHolderDespesas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDespesas holder, int position) {
        holder.txtDescricao.setText(listDespesas.get(position).getDescricao());
        holder.txtData.setText(UtilDate.getDateFormatted(listDespesas.get(position).getData()));
        holder.txtValor.setText(numberFormat.format(listDespesas.get(position).getValor()));
    }

    @Override
    public int getItemCount() {
        return listDespesas.size();
    }

    public class ViewHolderDespesas extends RecyclerView.ViewHolder {

        protected TextView txtDescricao;
        protected TextView txtData;
        protected TextView txtValor;

        public ViewHolderDespesas(View itemView) {
            super(itemView);
            txtDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
            txtData = (TextView) itemView.findViewById(R.id.txtData);
            txtValor = (TextView) itemView.findViewById(R.id.txtValor);
        }

    }

}
