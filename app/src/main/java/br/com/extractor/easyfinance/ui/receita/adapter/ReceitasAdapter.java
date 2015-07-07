package br.com.extractor.easyfinance.ui.receita.adapter;

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

public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ViewHolderReceitas> {

    private RealmResults<Receita> listReceitas;
    private NumberFormat numberFormat;

    public ReceitasAdapter() {
        listReceitas = Realm.getDefaultInstance().where(Receita.class).findAll();
        numberFormat = NumberFormat.getCurrencyInstance();
    }

    @Override
    public ViewHolderReceitas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, parent, false);
        return new ViewHolderReceitas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderReceitas holder, int position) {
        Receita receita = listReceitas.get(position);
        holder.txtDescricao.setText(receita.getDescricao());
        holder.txtData.setText(SimpleDateFormat.getDateInstance().format(receita.getData()));
        holder.txtValor.setText(numberFormat.format(receita.getValor()));
    }

    @Override
    public int getItemCount() {
        return listReceitas.size();
    }

    public class ViewHolderReceitas extends RecyclerView.ViewHolder {

        protected TextView txtDescricao;
        protected TextView txtData;
        protected TextView txtValor;

        public ViewHolderReceitas(View itemView) {
            super(itemView);
            txtDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
            txtData = (TextView) itemView.findViewById(R.id.txtData);
            txtValor = (TextView) itemView.findViewById(R.id.txtValor);
        }

    }

}
