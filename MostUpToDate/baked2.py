from __future__ import absolute_import, division, print_function, unicode_literals

# TensorFlow and tf.keras
import tensorflow as tf
from tensorflow import keras

# Helper libraries
import numpy as np
import matplotlib.pyplot as plt

#image stuff
from imutils import paths
import cv2
import os
import random

import argparse

import sys

ap = argparse.ArgumentParser()
ap.add_argument("-i", "--image", required=True, 
    help="file path to the image folder to be predicted")
ap.add_argument("-c", "--checkpoint", required=True,
    help="file path to the tensor checkpoints")
args = vars(ap.parse_args())

predict_data = []

#predictImagePaths = sorted(list(paths.list_images("predict_images")))

# for predictPath in predictImagePaths:
#     image = cv2.imread(predictPath)
#     image = cv2.resize(image, (28, 28))
#     predict_data.append(image)

# x_predict = np.array(predict_data)

model = keras.Sequential([
    keras.layers.Input((28, 28, 3)),
    keras.layers.Dense(60, activation=tf.nn.relu),
    keras.layers.Conv2D(16, (5, 5), padding="same", activation=tf.nn.relu),
    keras.layers.MaxPool2D((2, 2), 2),
    keras.layers.Dense(90, activation=tf.nn.relu),
    keras.layers.Conv2D(32, (5, 5), padding="same", activation=tf.nn.relu),
    keras.layers.MaxPool2D((2, 2), 2),
    keras.layers.Conv2D(64, (5, 5), padding="same", activation=tf.nn.relu),
    keras.layers.MaxPool2D((2, 2), 2),
    #keras.layers.Dropout(0.22),
    keras.layers.Flatten(),
    keras.layers.Dense(135, activation=tf.nn.relu),
    keras.layers.Dense(4, activation=tf.nn.softmax)
])

model.compile(
    optimizer='adam',
    loss='sparse_categorical_crossentropy',
    metrics=['accuracy']
)

model.summary()

checkpoint_path = "training_7/cp.ckpt"
checkpoint_dir = os.path.dirname(checkpoint_path)

model.load_weights(args["checkpoint"])

while True:
    s = sys.stdin.readline().strip()
    if s.contains(".png"):
        output = open("output.txt", "w")
        image = cv2.imread(args["image"] + os.path.sep + s)
        image = cv2.resize(image, (28, 28))
        images = []
        images.append(image)
        x_predict = np.array(images)
        prediction = np.argmax(model.predict(x_predict))
        output.write(str(prediction))
        output.close()
        sys.stdout.write("done")
        sys.stdout.flush()

# predictions = model.predict(x_predict)

# readable_predicts = []

# for prediction in predictions:
#     readable_predicts.append(np.argmax(prediction))

# print(predictImagePaths)
# print(readable_predicts)