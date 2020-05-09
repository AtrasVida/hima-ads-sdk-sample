package com.himaads.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pintoads.himasdk.Hima
import com.pintoads.himasdk.interstitial.AdListener
import com.pintoads.himasdk.interstitial.HimaAdListener
import com.pintoads.himasdk.interstitial.HimaAdListenerBuilder
import com.pintoads.himasdk.rewarded.HimaAdRewardedListener
import com.pintoads.himasdk.rewarded.RewardedListenerBuilder
import com.pintoads.himasdk.rewarded.RewardedVideoHimaAd


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rewarded.setOnClickListener {
            Hima(this,true).loadVideo { rev -> getRewardedListener(rev) }
        }

        interstitial.setOnClickListener {
            Hima(this).loadInterstitial { ad -> getInterstitialListener(ad) }
        }


        Hima(this).loadBannerTo(findViewById(R.id.fl_add)) {
            HimaAdListenerBuilder()
                .onAdLoaded { }
                .onAdClicked {
                    Toast.makeText(
                        this, "onAdClicked", Toast.LENGTH_SHORT
                    ).show()
                }
                .onAdFailedToLoad { }
                .onAdOpened {
                    Toast.makeText(
                        this, "onAdOpened2", Toast.LENGTH_SHORT
                    ).show()
                }
                .onAdLeftApplication { }
        }

        /*  Hima(this).loadBannerTo(findViewById(R.id.fl_add)) {
               HimaAdListenerBuilder(
                  onAdLoaded = {},
               onAdFailedToLoad = { e -> },
               onAdOpened = {},
               onAdClicked = {},
                onAdLeftApplication = {},
               onAdClosed = {}
            )
        }*/

    }

  private fun getInterstitialListener(ad: AdListener): HimaAdListener {

        return HimaAdListenerBuilder()
            .onAdLoaded {
                if (ad.isLoaded())
                    ad.show()
            }
            .onAdClicked { }
            .onAdFailedToLoad {
                Toast.makeText(this, "onAdFailedToLoad", Toast.LENGTH_SHORT).show()
            }
            .onAdOpened { }
            .onAdLeftApplication { }
    }

    private fun getRewardedListener(rev: RewardedVideoHimaAd): HimaAdRewardedListener {

        return RewardedListenerBuilder()
            .onRewardedVideoAdLoaded {

                if (rev.isLoaded())
                    rev.show()

            }.onRewardedVideoAdClosed {

            }.onRewarded { rewardedItem ->

                Toast.makeText(
                    this,
                    "onRewarded! currency: ${rewardedItem!!.type} amount: ${rewardedItem.amount}",
                    Toast.LENGTH_SHORT
                ).show()

            }.onRewardedVideoAdFailedToLoad {

            }.onRewardedVideoAdOpened {

            }.onRewardedVideoAdLeftApplication {

            }.onRewardedVideoStarted {

            }.onRewardedVideoCompleted { }
    }
}


/*

 lateinit var rev: RewardedVideoHimaAd
  val rewardedListener = RewardedListenerBuilder()
      .onRewardedVideoAdLoaded {
          if (rev.isLoaded())
              rev.show()

      }.onRewardedVideoAdClosed {

      }.onRewarded { rewardedItem ->

          Toast.makeText(
              this,
              "onRewarded! currency: ${rewardedItem!!.type} amount: ${rewardedItem.amount}",
              Toast.LENGTH_SHORT
          ).show()

      }.onRewardedVideoAdFailedToLoad {

      }.onRewardedVideoAdOpened {

      }.onRewardedVideoAdLeftApplication {

      }.onRewardedVideoStarted {

      }.onRewardedVideoCompleted {

      }
  Hima(this).loadVideo {
      rev = it
    rewardedListener
  }
*/
