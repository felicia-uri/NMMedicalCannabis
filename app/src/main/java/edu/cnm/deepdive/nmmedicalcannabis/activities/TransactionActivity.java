package edu.cnm.deepdive.nmmedicalcannabis.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.entities.TransactionDatabase;
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

    private final List<TransactionDatabase> mValues;

    public SimpleItemRecyclerViewAdapter(List<TransactionDatabase> items) {
      mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.transaction_cards, parent, false);

      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.mItem = mValues.get(position);
      holder.dispensary.setText(mValues.get(position).getPurchasedFrom());

      holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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

//      public final
      public TransactionDatabase mItem;

      public ViewHolder(View view) {
        super(view);
        mView = view;
        dispensary = (TextView) view.findViewById(R.id.dispensaryName);
      }
    }
  }

}
