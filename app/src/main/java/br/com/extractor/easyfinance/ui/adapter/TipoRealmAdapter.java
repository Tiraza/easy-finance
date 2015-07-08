package br.com.extractor.easyfinance.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import br.com.extractor.easyfinance.model.Tipo;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class TipoRealmAdapter extends RealmBaseAdapter<Tipo> implements ListAdapter {

    public TipoRealmAdapter(Context context, RealmResults<Tipo> realmResults) {
        super(context, realmResults, true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TipoSpinnerAdapterViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1,
                    parent, false);
            viewHolder = new TipoSpinnerAdapterViewHolder();
            viewHolder.descricao = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TipoSpinnerAdapterViewHolder) convertView.getTag();
        }

        Tipo item = realmResults.get(position);
        viewHolder.descricao.setText(item.getDescricao());

        return convertView;
    }

    public RealmResults<Tipo> getRealmResults() {
        return realmResults;
    }

    private static class TipoSpinnerAdapterViewHolder {
        TextView descricao;
    }

}