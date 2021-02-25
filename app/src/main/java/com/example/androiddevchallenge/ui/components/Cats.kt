/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.data.models.Cat
import com.example.androiddevchallenge.data.models.Gender
import com.example.androiddevchallenge.ui.theme.blue
import com.example.androiddevchallenge.ui.theme.pink
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CatListItem(
    cat: Cat,
    modifier: Modifier = Modifier,
    onCatSelected: (Long) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onCatSelected(cat.id) })
            .padding(horizontal = 24.dp, vertical = 14.dp)
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            data = cat.imageURL,
            contentDescription = "Cat image",
            requestBuilder = { transformations(CircleCropTransformation()) },
            modifier = Modifier.size(64.dp),
            fadeIn = true
        )
        Column(
            modifier = Modifier
                .weight(1f, true)
                .padding(start = 16.dp)
        ) {
            Text(
                text = cat.name,
                style = MaterialTheme.typography.h6
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = cat.location,
                    style = MaterialTheme.typography.caption.copy(fontSize = 14.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Column(
            modifier = Modifier.padding(start = 16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${cat.age} y/o",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(5.dp))
            Gender(cat.gender)
        }
    }
}

@Composable
fun Gender(
    gender: Gender,
    textStyle: TextStyle = MaterialTheme.typography.caption.copy(fontSize = 14.sp)
) {
    val color = when (gender) {
        Gender.Male -> blue
        Gender.Female -> pink
    }
    val backgroundModifier = Modifier.background(
        shape = MaterialTheme.shapes.small,
        color = color.copy(alpha = 0.1f)
    )
    Box(modifier = backgroundModifier.padding(horizontal = 2.dp)) {
        Text(
            text = gender.toString(),
            style = textStyle,
            textAlign = TextAlign.End,
            color = color,
        )
    }
}
