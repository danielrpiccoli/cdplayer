import React from 'react';
import { View, Text, StyleSheet, Image } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';

const cdPlayerIcon = require('../../assets/Cd-image-v1.png');

const TitleBar = () => {
  return (
    <LinearGradient
      colors={['#522436', '#AF4569']} 
      start={{ x: 0, y: 0 }} // Ponto inicial do degradê (topo esquerdo)
      end={{ x: 1, y: 0 }} // Ponto final (topo direito)
      style={styles.titleBar}
    >
      <View style={styles.titleContent}>
        <Image source={cdPlayerIcon} style={styles.icon} />
        <Text style={styles.titleText}>CD Player</Text>
      </View>
      <View style={styles.controlButtons}>
        <View style={styles.button}>
          <Text style={styles.buttonText}>_</Text>
        </View>
        <View style={styles.button}>
          <Text style={styles.buttonText}>□</Text>
        </View>
        <View style={styles.button}>
          <Text style={styles.buttonText}>X</Text>
        </View>
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
    // Adicionamos as bordas aqui para manter o efeito 3D
    borderTopColor: '#000000',
    borderLeftColor: '#000000',
    borderBottomColor: '#FFFFFF',
    borderRightColor: '#FFFFFF',
    borderWidth: 2,
  },
  titleContent: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  icon: {
    width: 16,
    height: 16,
    marginRight: 5,
  },
  titleText: {
    color: '#FFFFFF',
    fontWeight: 'bold',
    fontSize: 14,
    // Adicione a fonte personalizada aqui
    // fontFamily: 'ms-sans-serif', 
  },
  controlButtons: {
    flexDirection: 'row',
  },
  button: {
    backgroundColor: '#C0C0C0',
    width: 20,
    height: 20,
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 3,
    borderTopColor: '#FFFFFF',
    borderLeftColor: '#FFFFFF',
    borderBottomColor: '#8B8B8B',
    borderRightColor: '#8B8B8B',
    borderWidth: 1,
  },
  buttonText: {
    color: '#000000',
    fontWeight: 'bold',
  },
});

export default TitleBar;

