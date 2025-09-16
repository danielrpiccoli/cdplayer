import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const TitleBar = () => {
    return(
        <View style={styles.titleBar}>
            <Text style={styles.titleText}>CD Player</Text>
            <View style={styles.controlButtons}>
                <Text style={styles.buttonText}>_</Text>
                <Text style={styles.buttonText}>□</Text>
                <Text style={styles.buttonText}>X</Text>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    titleBar: {
        backgroundColor: '#000080',
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