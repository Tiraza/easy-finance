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

public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ViewHolderReceitas> {

    private RealmResults<Receita> listReceitas;
    private NumberFormat numberFormat;

    public ReceitasAdapter(Context context) {
        listReceitas = Realm.getInstance(context).where(Receita.class).findAll();
        numberFormat = NumberFormat.getCurrencyInstance();
    }

    @Override
    public ViewHolderReceitas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, parent, false);
        return new ViewHolderReceitas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderReceitas holder, int position) {
        holder.txtDescricao.setText(listReceitas.get(position).getDescricao());
        holder.txtData.setText(UtilDate.getDateFormatted(listReceitas.get(position).getData()));
        holder.txtValor.setText(numberFormat.format(listReceitas.get(position).getValor()));
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
