package com.melikeey.ui.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.melikeey.R;
import com.melikeey.base.BaseNavigator;
import com.melikeey.base.BaseViewHolder;
import com.melikeey.databinding.ItemSearchBinding;
import com.melikeey.model.Data;
import com.melikeey.ui.album.AlbumFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<BaseViewHolder> implements Filterable {

    private SearchFragment searchFragment;

    private List<Data> searchResponseList = new ArrayList<>();

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemSearchBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_search, viewGroup, false);

        return new BodyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    void setFragment(SearchFragment fragment) {
        this.searchFragment = fragment;
    }

    void addSearchResponseList(List<Data> searchResponseList) {
        this.searchResponseList.clear();
        this.searchResponseList.addAll(searchResponseList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return searchResponseList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Data> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(searchResponseList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Data item : searchResponseList) {
                    if (item.getArtist().getName().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            searchResponseList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class BodyViewHolder extends BaseViewHolder {

        ItemSearchBinding mBinding;

        BodyViewHolder(ItemSearchBinding binding) {
            super(binding.getRoot());

            this.mBinding = binding;
        }

        @Override
        protected void clear() {
        }

        @Override
        public void onBind(final int position) {
            super.onBind(position);

            String artistAndSongName = searchResponseList.get(position).getArtist().getName() + " - " + searchResponseList.get(position).getTitle();

            mBinding.tvArtistName.setText(artistAndSongName);

            Picasso.get().load(searchResponseList.get(position).getArtist().getPicture_medium()).into(mBinding.ivArtist);

            mBinding.getRoot().setOnClickListener(view ->
                    searchFragment.mPresenter.openFragment(AlbumFragment.newInstance(searchResponseList.get(position).getArtist()), BaseNavigator.Transaction.REPLACE, true));
        }
    }
}