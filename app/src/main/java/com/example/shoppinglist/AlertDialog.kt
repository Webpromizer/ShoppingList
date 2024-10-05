package com.example.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

data class Item(var id: Int, var qnt: Int, var name: String)



@Composable
fun AlertDialogFun(onDismiss: () -> Unit, sItems: MutableList<Item>) {
    var itemName by remember { mutableStateOf("") }
    var itemQ by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        text = {
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Item")
                TextField(
                    value = itemName,
                    onValueChange = { itemName = it }
                )
                Text("Qnt")
                TextField(
                    value = itemQ,
                    onValueChange = { itemQ = it }
                )
            }
        },
        title = { Text("New Item") },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    if (itemName.isNotBlank() && itemQ.isNotBlank()) {
                        val newItem = Item(
                            id = sItems.size + 1,
                            name = itemName,
                            qnt = itemQ.toInt()
                        )
                        sItems.add(newItem)
                        onDismiss()
                        itemName = ""
                        itemQ = ""
                    }
                }) {
                    Text("Add")
                }
                Button(onClick = { onDismiss() }) {
                    Text("Cancel")
                }
            }
        }
    )
}

@Composable
fun shopListItem(
    item: Item,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit)
{
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .border(
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(20)
        )){
        Text(text = item.name, modifier = Modifier.padding(8.dp))
    }
}



