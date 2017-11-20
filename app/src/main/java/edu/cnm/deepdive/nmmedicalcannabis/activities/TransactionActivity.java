package edu.cnm.deepdive.nmmedicalcannabis.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.entities.TransactionDatabaseTable;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper;
import java.sql.SQLException;
import java.util.List;

public class TransactionActivity extends AppCompatActivity implements OrmHelper.OrmInteraction {

  private OrmHelper helper = null;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.page_transactions_layout);

    RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    View recyclerView = findViewById(R.id.recycler_view);
    assert recyclerView != null;
    setupRecyclerView((RecyclerView) recyclerView);

  }

  private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
    try {
      recyclerView.setAdapter(
          new SimpleItemRecyclerViewAdapter(getHelper().getTransactionsDao().queryForAll()));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  protected void onStart() {
    super.onStart();
    getHelper();
  }

  @Override
  protected void onStop() {
    releaseHelper();
    super.onStop();
  }

  @Override
  public synchronized OrmHelper getHelper() {
    if (helper == null) {
      helper = OpenHelperManager.getHelper(this, OrmHelper.class);
    }
    return helper;
  }

  public synchronized void releaseHelper() {
    if (helper != null) {
      OpenHelperManager.releaseHelper();
      helper = null;
    }
  }

  public class SimpleItemRecyclerViewAdapter
      extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<TransactionDatabaseTable> mValues;

    public SimpleItemRecyclerViewAdapter(List<TransactionDatabaseTable> items) {
      mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.transaction_cards, parent, false);

      //TODO add view holder for patient card information
//      View view1 = LayoutInflater.from(parent.getContext()).inflate()

      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
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
