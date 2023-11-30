package com.rdt.sepatuku.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rdt.sepatuku.R
import com.rdt.sepatuku.di.Injection
import com.rdt.sepatuku.model.OrderItem
import com.rdt.sepatuku.ui.ViewModelFactory
import com.rdt.sepatuku.ui.common.UiState
import com.rdt.sepatuku.ui.components.Items

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sepatuku",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text(stringResource(R.string.search)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.searchItems(searchText)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllItems()
                }

                is UiState.Success -> {
                    HomeContent(
                        orderItem = uiState.data,
                        searchText =searchText,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                    )
                }

                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun HomeContent(
    orderItem: List<OrderItem>,
    searchText: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    val filteredProducts = orderItem.filter {
        it.Item.title.contains(searchText, ignoreCase = true)
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(filteredProducts) { data ->
            Items(
                image = data.Item.image,
                title = data.Item.title,
                price = data.Item.price,
                modifier = Modifier.clickable {
                    navigateToDetail(data.Item.id)
                }
            )
        }
    }
}