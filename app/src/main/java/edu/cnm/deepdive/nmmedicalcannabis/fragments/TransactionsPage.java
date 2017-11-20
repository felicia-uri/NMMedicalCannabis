package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.activities.TransactionActivity;
import edu.cnm.deepdive.nmmedicalcannabis.entities.TransactionDatabaseTable;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.util.List;


public class TransactionsPage extends Fragment implements OnClickListener {

  private AutoCompleteTextView dispensaryName;
  private AutoCompleteTextView strainName;
  private EditText gramsAmount;



  public TransactionsPage() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    View inflate = inflater.inflate(R.layout.page_transactions_layout, container, false);
    RecyclerView recyclerView = inflate.findViewById(R.id.recycler_view);
    setupRecyclerView(recyclerView);

    getActivity().findViewById(R.id.add_transaction_button).setOnClickListener(this);


    return inflate;

  }

  @Override
  public void onClick(View v) {
    NewTransactionDialog transactionDialog = new NewTransactionDialog();
    transactionDialog.show(getActivity().getSupportFragmentManager(), "new transaction");

  }


  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    try {
      Dao<TransactionDatabaseTable, Integer> dao = ((OrmInteraction) getActivity()).getHelper().getTransactionsDao();
      QueryBuilder<TransactionDatabaseTable, Integer> builder = dao.queryBuilder();
      builder.orderBy("PURCHASED_DATE", false);
      List<TransactionDatabaseTable> transactionDatabaseTables = dao.query(builder.prepare());
      recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(transactionDatabaseTables));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public class SimpleItemRecyclerViewAdapter
      extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<TransactionDatabaseTable> mValues;

    public SimpleItemRecyclerViewAdapter(List<TransactionDatabaseTable> items) {
      mValues = items;
    }

    @Override
    public SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.transaction_cards, parent, false);

      //TODO add view holder for patient card information
//      View view1 = LayoutInflater.from(parent.getContext()).inflate()

      return new SimpleItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
      holder.mItem = mValues.get(position);
      holder.dispensary.setText(mValues.get(position).getPurchasedFrom());
      holder.grams.setText(Integer.toString(mValues.get(position).getUnitsPurchased()));
      holder.strain.setText(mValues.get(position).getStrainName());


      holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Context context = v.getContext();
//            Intent intent = new Intent(context, TransactionActivity.class);
//            intent.putExtra(TransactionDatabaseTable.class, holder.mItem.getId());
//            context.startActivity(intent);
        }
      });
    }


    @Override
    public int getItemCount() {
      return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

      public final View mView;
      public final TextView dispensary;
      public final TextView strain;
      public final TextView grams;
      public TransactionDatabaseTable mItem;

      public ViewHolder(View view) {
        super(view);
        mView = view;
        dispensary = (TextView) view.findViewById(R.id.dispensaryName);
        strain = (TextView) view.findViewById(R.id.strainName);
        grams = (TextView) view.findViewById(R.id.units_grams);
      }
    }
  }
}
