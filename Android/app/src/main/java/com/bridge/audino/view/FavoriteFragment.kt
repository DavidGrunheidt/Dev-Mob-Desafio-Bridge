package com.bridge.audino.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bridge.audino.adapter.FavoriteTrackAdapter
import com.bridge.audino.adapter.TrackListAdapter
import com.bridge.audino.databinding.FavoritesFragmentBinding
import com.bridge.audino.model.Track
import com.bridge.audino.viewmodel.FavoriteTrackListViewModel
import com.bridge.audino.viewmodel.MusicPlayerViewModel.Companion.FROM_FAVORITES
import com.bridge.audino.viewmodel.MusicPlayerViewModel.Companion.FROM_ID
import com.bridge.audino.viewmodel.MusicPlayerViewModel.Companion.TRACK_ID

class FavoriteFragment : TrackListFragment() {
    private lateinit var mBinding: FavoritesFragmentBinding
    private val mFavoriteViewModel: FavoriteTrackListViewModel by lazy {
        ViewModelProvider(this).get(
            FavoriteTrackListViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FavoritesFragmentBinding.inflate(LayoutInflater.from(context), container, false)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        showProgressBar()
        mFavoriteViewModel.init()
    }

    override fun onStart() {
        super.onStart()
        mBinding.favoriteList.adapter = FavoriteTrackAdapter(this, mFavoriteViewModel, this)
        mFavoriteViewModel.getTrackList().observe(viewLifecycleOwner, Observer<List<Track>> {
            (mBinding.favoriteList.adapter as TrackListAdapter).setTrackList(it)
            if (it.isEmpty()) {
                showEmptyLayout()
            } else {
                showList()
            }
        })
    }

    private fun showList() {
        mBinding.favoritosProgressbar.visibility = View.GONE
        mBinding.emptyLayoutGroup.visibility = View.GONE
        mBinding.favoriteList.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        mBinding.emptyLayoutGroup.visibility = View.GONE
        mBinding.favoriteList.visibility = View.GONE
        mBinding.favoritosProgressbar.visibility = View.VISIBLE
    }

    private fun showEmptyLayout() {
        mBinding.favoritosProgressbar.visibility = View.GONE
        mBinding.favoriteList.visibility = View.GONE
        mBinding.emptyLayoutGroup.visibility = View.VISIBLE
    }

    override fun onTrackClicked(track: Track) {
        val intent = Intent(context, MusicPlayerActivity::class.java).apply {
            putExtra(TRACK_ID, track.id)
            putExtra(FROM_ID, FROM_FAVORITES)
        }
        context?.startActivity(intent)
    }
}