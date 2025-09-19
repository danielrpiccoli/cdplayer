import React from 'react';
import { View, Text, StyleSheet, Image } from 'react-native';
import TitleBar from '../components/titleBar';

const mockAlbumArt = require('../../assets/nightInQuestionMock.png');

const PlayerScreen = () => {
    return (
        <View style={styles.mainContainer}>
            <View style={styles.playerWindow}>
                <TitleBar />
                <View style={styles.playerBody}>
                    <View style={styles.albumArtContainer}>
                        <Image source={mockAlbumArt} style={styles.albumArt} />
                    </View>
                </View>
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
        width: 352,
        height: 159,
        backgroundColor: '#DFDFDF',

        borderTopColor: '#FFFFFF',
        borderLeftColor: '#FFFFFF',
        borderBottomColor: '#8B8B8B',
        borderRightColor: '8B8B8B',
        borderWidth: 2,
    },
    playerBody:{
        flexDirection: 'row',
        padding: 10,
        alignItems: 'center',
    },
    albumArtContainer: {
        width: 100,
        height: 100,
        borderWidth: 2,
        borderColor: '#000000',
        justifyContent: 'center',
        alignItems: 'center',
    },
    albumArt: {
        width: '100%',
        height: '100%',
        resizeMode: 'cover',
    },
    
});

export default PlayerScreen;