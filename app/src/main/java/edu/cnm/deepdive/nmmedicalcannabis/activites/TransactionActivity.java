package edu.cnm.deepdive.nmmedicalcannabis.activites;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.nmmedicalcannabis.R;
import edu.cnm.deepdive.nmmedicalcannabis.entities.Transactions;
import edu.cnm.deepdive.nmmedicalcannabis.helpers.OrmHelper;
import java.sql.SQLException;
import java.util.List;

public class TransactionActivity extends AppCompatActivity implements OrmHelper.OrmInteraction {

  private OrmHelper helper = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transaction);

    View recyclerView = findViewById(R.id.transaction_list);
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

    private final List<Transactions> mValues;

    public SimpleItemRecyclerViewAdapter(List<Transactions> items) {
      mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.transaction_list_items, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.mItem = mValues.get(position);
      holder.mPurchasedFromView.setText(mValues.get(position).getPurchasedFrom());
      holder.mUnitsPurchasedView.setText(Integer.toString(mValues.get(position).getUnitsPurchased()));
      holder.mStrainNameView.setText(mValues.get(position).getStrainName());

      holder.mView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Context context = v.getContext();
//            Intent intent = new Intent(context, StudentDetailActivity.class);
//            intent.putExtra(StudentDetailFragment.STUDENT_ID, holder.mItem.getId());
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
      public final TextView mPurchasedFromView;
      public final TextView mUnitsPurchasedView;
      public final TextView mStrainNameView;
      public Transactions mItem;

      public ViewHolder(View view) {
        super(view);
        mView = view;
        mPurchasedFromView = (TextView) view.findViewById(R.id.purchased_from);
        mUnitsPurchasedView = (TextView) view.findViewById(R.id.units_purchased);
        mStrainNameView = (TextView) view.findViewById(R.id.strain_name);
      }

      @Override
      public String toString() {
        return super.toString() + " '" + mPurchasedFromView.getText() + "'";
      }
    }
  }

  }
