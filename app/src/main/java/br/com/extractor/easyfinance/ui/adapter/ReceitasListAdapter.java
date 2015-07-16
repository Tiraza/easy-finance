package br.com.extractor.easyfinance.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.model.Receita;
import io.realm.Realm;
import io.realm.RealmResults;

public class ReceitasListAdapter extends RecyclerView.Adapter<ReceitasListAdapter.ViewHolderReceitas> {

    private final View.OnClickListener itemClickEvent;
    private final View.OnLongClickListener itemLongClickEvent;
    private final RealmResults<Receita> listReceitas;
    private final NumberFormat numberFormat;

    public ReceitasListAdapter(View.OnClickListener itemClickEvent, View.OnLongClickListener itemLongClickEvent) {
        this.itemClickEvent = itemClickEvent;
        this.itemLongClickEvent = itemLongClickEvent;
        this.listReceitas = Realm.getDefaultInstance().where(Receita.class).findAll();
        this.numberFormat = NumberFormat.getCurrencyInstance();
    }

    @Override
    public ViewHolderReceitas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .receita_item_listview, parent, false);
        view.setOnLongClickListener(itemLongClickEvent);
        view.setOnClickListener(itemClickEvent);
        return new ViewHolderReceitas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderReceitas holder, int position) {
        Receita receita = listReceitas.get(position);
        holder.txtDescricao.setText(receita.getDescricao());
        holder.txtData.setText(SimpleDateFormat.getDateInstance().format(receita.getDataPaga()));
        holder.txtValor.setText(numberFormat.format(receita.getValorPago()));
        holder.txtId.setText(String.valueOf(receita.getId()));
        holder.txtTipo.setText(receita.getTipo().getDescricao());
    }

    @Override
    public int getItemCount() {
        return listReceitas.size();
    }

    public class ViewHolderReceitas extends RecyclerView.ViewHolder {

        protected final TextView txtDescricao;
        protected final TextView txtData;
        protected final TextView txtValor;
        protected final TextView txtId;
        protected final TextView txtTipo;

        public ViewHolderReceitas(View itemView) {
            super(itemView);
            txtDescricao = (TextView) itemView.findViewById(R.id.txt_descricao_receita);
            txtData = (TextView) itemView.findViewById(R.id.txt_data_receita);
            txtValor = (TextView) itemView.findViewById(R.id.txt_valor_receita);
            txtId = (TextView) itemView.findViewById(R.id.txt_id);
            txtTipo = (TextView) itemView.findViewById(R.id.txt_tipo_receita);
        }

    }

}

