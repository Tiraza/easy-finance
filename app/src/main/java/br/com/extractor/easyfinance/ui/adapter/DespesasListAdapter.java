package br.com.extractor.easyfinance.ui.adapter;

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

public class DespesasListAdapter extends RecyclerView.Adapter<DespesasListAdapter.ViewHolderDespesas> {

    private final View.OnClickListener itemClickEvent;
    private final View.OnLongClickListener itemLongClickEvent;
    private final RealmResults<Despesa> listDespesas;
    private final NumberFormat numberFormat;

    public DespesasListAdapter(View.OnClickListener itemClickEvent, View.OnLongClickListener itemLongClickEvent) {
        this.itemClickEvent = itemClickEvent;
        this.itemLongClickEvent = itemLongClickEvent;
        this.listDespesas = Realm.getDefaultInstance().where(Despesa.class).findAll();
        this.numberFormat = NumberFormat.getCurrencyInstance();
    }

    @Override
    public ViewHolderDespesas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.despesa_item_listview, parent, false);
        view.setOnLongClickListener(itemLongClickEvent);
        view.setOnClickListener(itemClickEvent);
        return new ViewHolderDespesas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDespesas holder, int position) {
        Despesa despesa = listDespesas.get(position);
        holder.txtDescricao.setText(despesa.getDescricao());
        holder.txtData.setText(SimpleDateFormat.getDateInstance().format(despesa.getDataPaga()));
        holder.txtValor.setText(numberFormat.format(despesa.getValorPago()));
        holder.txtId.setText(String.valueOf(despesa.getId()));
        holder.txtTipo.setText(despesa.getTipo().getDescricao());
    }

    @Override
    public int getItemCount() {
        return listDespesas.size();
    }

    public class ViewHolderDespesas extends RecyclerView.ViewHolder {

        protected final TextView txtDescricao;
        protected final TextView txtData;
        protected final TextView txtValor;
        protected final TextView txtId;
        protected final TextView txtTipo;

        public ViewHolderDespesas(View itemView) {
            super(itemView);
            txtDescricao = (TextView) itemView.findViewById(R.id.txt_descricao_despesa);
            txtData = (TextView) itemView.findViewById(R.id.txt_data_despesa);
            txtValor = (TextView) itemView.findViewById(R.id.txt_valor_despesa);
            txtId = (TextView) itemView.findViewById(R.id.txt_id);
            txtTipo = (TextView) itemView.findViewById(R.id.txt_tipo_despesa);
        }

    }

}
