package br.com.extractor.easyfinance.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.com.extractor.easyfinance.model.domain.PanelType;

public class ChartTypeAdapter extends ArrayAdapter<PanelType> {

    private final Context context;

    public ChartTypeAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1, PanelType.values());
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(android.R.layout.simple_list_item_1, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(android.R.id.text1);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        PanelType panelType = PanelType.values()[position];

        holder.text.setText(context.getString(panelType.getTitleId()));

        return rowView;
    }

    static class ViewHolder {
        public TextView text;
    }

}
