package com.jin.firebasetest

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random


class FirebaseManager() {
    val firestoreInstance: FirebaseFirestore
    val random: Random

    init {
        firestoreInstance = FirebaseFirestore.getInstance()
        random = Random(500)
    }

    fun addRandomErrorData() {
        val data: MutableMap<String, Any> = HashMap()
        data["errorMsg"] = "a sample error msg"
        data["errorCode"] = random.nextInt(500)
        data["exception"] = "this is a sample exception!!"
        // Add a new document with a generated ID
        firestoreInstance.collection("NetworkErrorLogs")
            .add(data)
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                println("upload data success, id " + documentReference.id)
                println("upload data success, path " + documentReference.path)
                println("upload data success, parent " + documentReference.parent)
            })
            .addOnFailureListener(OnFailureListener { e ->
                println("fail upload data " + e)
            })
    }

    fun updateDocuments() {
        val data: MutableMap<String, Any> = HashMap()
        data["errorMsg"] = "a sample error msg"
        data["errorCode"] = random.nextInt(500)
        data["exception"] = "this is a sample exception!!"
        firestoreInstance.collection("NetworkErrorLogs2").document("a sample error msg")
            .set(data)
            .addOnSuccessListener(OnSuccessListener<Void?> {
                println("upload data success, id ")
            })
            .addOnFailureListener(OnFailureListener { e ->
                println("fail upload data " + e)
            })
    }

    fun counterAdded() {
        val shardRef: DocumentReference =
            firestoreInstance.collection("Counter").document("network error1")
        shardRef.update("count", FieldValue.increment(1))
        shardRef.update("user_id",FieldValue.arrayUnion(random.nextInt(10000)))
        shardRef.update("timestamp",FieldValue.serverTimestamp())
    }


    fun querySampleDoc() {
        val docRef: DocumentReference =
                    firestoreInstance.collection("NetworkErrorLogs")
                        .document("47sKvD07AhBshgj5W4gs")
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document!!.exists()) {
                   println("DocumentSnapshot data: " + document!!.data) //data is hash map format.
                } else {
                    println("No such document")
                }
            }
        }
    }

}