package br.com.extractor.easyfinance.ui.despesa.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.model.Despesa;
import io.realm.Realm;
import io.realm.RealmResults;

public class DespesasAdapter extends RecyclerView.Adapter<DespesasAdapter.ViewHolderDespesas> {

    private final View.OnClickListener itemClickEvent;
    private final View.OnLongClickListener itemLongClickEvent;
    private final RealmResults<Despesa> listDespesas;
    private final NumberFormat numberFormat;

    public DespesasAdapter(View.OnClickListener itemClickEvent, View.OnLongClickListener itemLongClickEvent) {
        this.itemClickEvent = itemClickEvent;
        this.itemLongClickEvent = itemLongClickEvent;
        this.listDespesas = Realm.getDefaultInstance().where(Despesa.class).findAll();
        this.numberFormat = NumberFormat.getCurrencyInstance();
    }

    @Override
    public ViewHolderDespesas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, parent, false);
        view.setOnLongClickListener(itemLongClickEvent);
        view.setOnClickListener(itemClickEvent);
        return new ViewHolderDespesas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDespesas holder, int position) {
        Despesa despesa = listDespesas.get(position);
        holder.txtDescricao.setText(despesa.getDescricao());
        holder.txtData.setText(SimpleDateFormat.getDateInstance().format(despesa.getData()));
        holder.txtValor.setText(numberFormat.format(despesa.getValor()));
        holder.txtId.setText(despesa.getId());
    }

    @Override
    public int getItemCount() {
        return listDespesas.size();
    }

    public class ViewHolderDespesas extends RecyclerView.ViewHolder {

        protected TextView txtDescricao;
        protected TextView txtData;
        protected TextView txtValor;
        protected TextView txtId;

        public ViewHolderDespesas(View itemView) {
            super(itemView);
            txtDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
            txtData = (TextView) itemView.findViewById(R.id.txtData);
            txtValor = (TextView) itemView.findViewById(R.id.txtValor);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
        }

    }

}
