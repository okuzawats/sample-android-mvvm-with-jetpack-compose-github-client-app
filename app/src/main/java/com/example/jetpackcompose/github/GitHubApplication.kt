package com.example.jetpackcompose.github

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 独自のApplicationクラス
 *
 * Hiltの @HiltAndroidApp を付与するために作成
 */
@HiltAndroidApp
class GitHubApplication : Application()