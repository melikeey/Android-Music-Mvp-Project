package com.melikeey.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melikeey.R;
import com.melikeey.base.BaseFragment;
import com.melikeey.databinding.FragmentSearchBinding;
import com.melikeey.di.component.ActivityComponent;
import com.melikeey.model.Data;

import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment implements SearchMvpView {

    @Inject
    SearchMvpPresenter<SearchMvpView> mPresenter;

    @Inject
    SearchAdapter searchAdapter;

    private String searchQuery;

    private FragmentSearchBinding mBinding;

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        ActivityComponent component = getActivityComponent();

        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }

        return mBinding.getRoot();
    }


    private void setUpRecyclerView() {
        mBinding.rv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());

        mBinding.rv.setLayoutManager(layoutManager);

        mBinding.rv.setAdapter(searchAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        hideToolbar();
    }

    @Override
    protected void setUp(View view) {

        mBinding.searchView.setQueryHint("Search");

        mBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.equals("")) {
                    searchQuery = newText;
                    mPresenter.getSearchResult(newText);
                    searchAdapter.getFilter().filter(searchQuery);
                }

                return false;
            }
        });
    }

    @Override
    public void setSearchResponse(List<Data> data) {

        if (data.size() == 0) {
            mBinding.tvTips.setVisibility(View.VISIBLE);
        } else {
            mBinding.tvTips.setVisibility(View.GONE);
        }

        hideToolbar();
        searchAdapter.addSearchResponseList(data);
        searchAdapter.notifyDataSetChanged();

        searchAdapter.setFragment(this);

        setUpRecyclerView();

        mBinding.tvHeader.setText(getResources().getString(R.string.artist));
    }
}
