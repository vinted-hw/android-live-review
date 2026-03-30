package com.vinted.demovinted.application

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.Downsampler
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.vinted.demovinted.R

@GlideModule
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(
            RequestOptions()
                .set(Downsampler.DECODE_FORMAT, DecodeFormat.PREFER_ARGB_8888)
                .encodeFormat(Bitmap.CompressFormat.WEBP)
                .skipMemoryCache(false)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .disallowHardwareConfig()
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
