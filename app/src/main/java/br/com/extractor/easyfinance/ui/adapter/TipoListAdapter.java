package br.com.extractor.easyfinance.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.model.Tipo;
import br.com.extractor.easyfinance.model.domain.CategoriaTitulo;
import io.realm.Realm;
import io.realm.RealmResults;

public class TipoListAdapter extends RecyclerView.Adapter<TipoListAdapter.ViewHolderTipo> {

    private final View.OnClickListener itemClickEvent;
    private final View.OnLongClickListener itemLongClickEvent;
    private final RealmResults<Tipo> listTipos;

    public TipoListAdapter(View.OnClickListener itemClickEvent, View.OnLongClickListener itemLongClickEvent) {
        this.itemClickEvent = itemClickEvent;
        this.itemLongClickEvent = itemLongClickEvent;
        this.listTipos = Realm.getDefaultInstance().where(Tipo.class).findAll();
    }

    @Override
    public ViewHolderTipo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tipo_item_listview,
                parent, false);
        view.setOnLongClickListener(itemLongClickEvent);
        view.setOnClickListener(itemClickEvent);
        return new ViewHolderTipo(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderTipo holder, int position) {
        Tipo tipo = listTipos.get(position);
        holder.txtDescricao.setText(tipo.getDescricao());
        holder.txtId.setText(String.valueOf(tipo.getId()));
        holder.txtTipo.setText(CategoriaTitulo.getById(tipo.getCategoria()).getDescricao());
    }

    @Override
    public int getItemCount() {
        return listTipos.size();
    }

    public static class ViewHolderTipo extends RecyclerView.ViewHolder {

        protected TextView txtDescricao;
        protected TextView txtTipo;
        protected TextView txtId;

        public ViewHolderTipo(View itemView) {
            super(itemView);
            txtDescricao = (TextView) itemView.findViewById(R.id.txt_descricao_tipo);
            txtTipo = (TextView) itemView.findViewById(R.id.txt_categoria_tipo);
            txtId = (TextView) itemView.findViewById(R.id.txt_id);
        }

    }

}
