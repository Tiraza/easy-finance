package extractor.com.easyfinance.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import extractor.com.easyfinance.model.Despesa;
import extractor.com.myapplication.R;

/**
 * @author Muryllo Tiraza
 */
public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.MyViewHolder> {
    private ArrayList<Despesa> despesas;
    private LayoutInflater mLayoutInflater;

    public CommonAdapter(Context context, ArrayList<Despesa> despesas){
       this.despesas = despesas;
       mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_recyclerview, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.txtData.setText(despesas.get(position).getData());
        myViewHolder.txtDescricao.setText(despesas.get(position).getDescricao());
        myViewHolder.txtValor.setText(despesas.get(position).getValor().toString());
    }

    @Override
    public int getItemCount() {
        return despesas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtDescricao;
        private TextView txtData;
        private TextView txtValor;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtData = (TextView) itemView.findViewById(R.id.txtData);
            txtDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
            txtValor = (TextView) itemView.findViewById(R.id.txtValor);
        }
    }
}
