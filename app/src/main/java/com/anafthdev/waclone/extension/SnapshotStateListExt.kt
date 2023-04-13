package com.anafthdev.waclone.extension

import androidx.compose.runtime.snapshots.SnapshotStateList


fun <T> SnapshotStateList<T>.swap(newValue: Collection<T>) {
	clear()
	addAll(newValue)
}

fun <T> SnapshotStateList<T>.swap(newValue: Array<T>) {
	clear()
	addAll(newValue)
}
