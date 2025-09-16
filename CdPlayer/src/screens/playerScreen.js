import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import TitleBar from '../components/titleBar';

const PlayerScreen = () => {
    return (
        <View style={styles.mainContainer}>
            <View style={styles.playerWindow}>
                <TitleBar />
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    mainContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#C0C0C0',
    },
    playerWindow: {
        width: 350,
        height: 250,
        backgroundColor: '#DFDFDF',

        borderTopColor: '#FFFFFF',
        borderLeftColor: '#FFFFFF',
        borderBottomColor: '#8B8B8B',
        borderRightColor: '8B8B8B',
        borderWidth: 2,
    },
});

export default PlayerScreen;