import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';

const TitleBar = () => {
  return (
    <LinearGradient
      colors={['#522436', '#AF4569']} // Array com as cores em hexadecimal
      start={{ x: 0, y: 0 }} // Ponto inicial do degradê (topo esquerdo)
      end={{ x: 1, y: 0 }} // Ponto final (topo direito)
      style={styles.titleBar}
    >
      <Text style={styles.titleText}>CD Player</Text>
      <View style={styles.controlButtons}>
        <Text style={styles.buttonText}>_</Text>
        <Text style={styles.buttonText}>□</Text>
        <Text style={styles.buttonText}>X</Text>
      </View>
    </LinearGradient>
  );
};

const styles = StyleSheet.create({
    titleBar: {
        height: 25,
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingHorizontal: 5,
    },
    titleText:
    {
        color: '#FFFFFF',
        fontWeight: 'bold',
        fontSize: 14,
        fontFamily: 'ms-sans-serif',
    },
    controlButtons: {
        flexDirection: 'row',
    },
    buttonText: {
        color: '#000000',
        backgroundColor: '#C0C0C0',
        width: 20,
        height: 20,
        textAlign: 'center',
        marginLeft: 3,
    },
});

export default TitleBar;