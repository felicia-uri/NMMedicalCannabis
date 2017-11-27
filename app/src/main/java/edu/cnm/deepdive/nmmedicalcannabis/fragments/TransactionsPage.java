package edu.cnm.deepdive.nmmedicalcannabis.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.stmt.QueryBuilder;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.entities.SubTransaction;
import edu.cnm.deepdive.nmmedicalcannabis.entities.TransactionDatabase;
import edu.cnm.deepdive.nmmedicalcannabis.fragments.NewTransactionDialog.Callback;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Text;

/**
 * Creates the transaction history page fragment, which lists all transactions made
 * by the user.
 */
public class TransactionsPage extends Fragment implements OnClickListener {

  private AutoCompleteTextView dispensaryName;
  private AutoCompleteTextView strainName;
  private EditText gramsAmount;

  private RecyclerView recyclerView;

  /**
   * Creates class constructor
   */
  public TransactionsPage() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    View inflate = inflater.inflate(R.layout.page_transactions_layout, container, false);
    recyclerView = inflate.findViewById(R.id.recycler_view);
    setupRecyclerView(recyclerView);

    getActivity().findViewById(R.id.add_transaction_button).setOnClickListener(this);

    try {
      double unitsAvailable = ((OrmInteraction) getActivity()).getHelper().getPatientCardDao()
          .queryForAll().get(0).getUnitsAvailable();
      ((TextView) inflate.findViewById(R.id.unitsLayout)).setText(Double.toString(unitsAvailable));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return inflate;

  }

  @Override
  public void onClick(View v) {

    NewTransactionDialog transactionDialog = new NewTransactionDialog();
    transactionDialog.setCallback(new Callback() {
      @Override
      public void refreshList() {
        setupRecyclerView(recyclerView);
      }
    });
    transactionDialog.show(getActivity().getSupportFragmentManager(), "new transaction");

  }


  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    try {
      Dao<TransactionDatabase, Integer> dao = ((OrmInteraction) getActivity()).getHelper().getTransactionsDao();
      QueryBuilder<TransactionDatabase, Integer> builder = dao.queryBuilder();
      builder.orderBy("PURCHASED_DATE", false);
      List<TransactionDatabase> transactionDatabases = dao.query(builder.prepare());
      recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(transactionDatabases));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Recycles views based on the transactions added.
   */
  public class SimpleItemRecyclerViewAdapter
      extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<TransactionDatabase> mValues;

    /**
     * Creates recycler view adapter to add all items to transaction history page.
     * @param items list of cannabis purchases
     */
    public SimpleItemRecyclerViewAdapter(List<TransactionDatabase> items) {
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
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
      holder.purchasedDate.setText(simpleDateFormat.format(mValues.get(position).getPurchasedDate()));

      ForeignCollection<SubTransaction> subTransaction = mValues.get(position).getSubTransaction();
      ArrayAdapter<SubTransaction> arrayAdapter = new ArrayAdapter<SubTransaction>(getContext(),
          android.R.layout.simple_list_item_1, new ArrayList<SubTransaction>(subTransaction));
      holder.cardList.setAdapter(arrayAdapter);

      holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Context context = v.getContext();
//            Intent intent = new Intent(context, TransactionActivity.class);
//            intent.putExtra(TransactionDatabase.class, holder.mItem.getId());
//            context.startActivity(intent);
        }
      });
    }


    @Override
    public int getItemCount() {
      return mValues.size();
    }

    /**
     * Creates cards for each transaction added.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

      /**
       * The view associated with the transaction.
       */
      public final View mView;
      /**
       * Text name for dispensary purchased from.
       */
      public final TextView dispensary;
      /**
       * Text name for date purchased
       */
      public final TextView purchasedDate;
      /**
       * Creates card list views.
       */
      public final ListView cardList;
      /**
       * Items from transaction database.
       */
      public TransactionDatabase mItem;


      /**
       *
       * @param view
       */
      public ViewHolder(View view) {
        super(view);
        mView = view;
        dispensary = (TextView) view.findViewById(R.id.dispensaryName);
        purchasedDate = view.findViewById(R.id.purchaseDateCardView);
        cardList = view.findViewById(R.id.cardList);
      }
    }
  }
}
