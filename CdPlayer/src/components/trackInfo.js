import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const TrackInfo = () => {
    return(
        <View style={styles.container}>
            <View style={styles.infoRow}>
                <Text style={styles.label}>Artist:</Text>
                <View style={style.inputField}>
                    <Text style={styles.text}>TV Girl</Text>
                </View>
                <Text style={styles.dropdown}>▼</Text>
            </View>

            <View style={styles.infoRow}>
                <Text style={styles.label}>Track:</Text>
                <View style={styles.inputField}>
                    <Text style={styles.text}>The Desolation Tango</Text>
                </View>
                <Text style={styles.dropdown}>▼</Text>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        marginLeft: 10,
    },
    infoRow: {
        flexDirection: 'row',
        alignItems: 'center',
        marginBottom: 5,
    },
    label: {
        width: 50,
        fontWeight: 'bold',
        // placeholder fonte: fontFamily: 'ms-sans-sherif',
    },
    inputField: {
        flex: 1,
        backgroundColor: '#FFFFFFF',
        height: 20,
        justifyContent: 'center',
        paddingHorizontal: 5,
        borderTopColor: '#8B8B8B',
        borderTopColor: '#8B8B8B',
        borderBottomColor: '#FFFFFF',
        borderRightColor: '#FFFFFF',
        borderWidth: 2,
    },
    text: {
        //fontFamily: 'ms-sans-sheriff',
    },
    dropdown: {
        marginLeft: 3,
        width: 15,
        height: 15,
        textAlign: 'center',
        color: '#000000',
        backgroundColor:'#C0C0C0',
        borderTopColor: '#FFFFFFF',
        borderLeftColor: '#FFFFFF',
        borderBottomColor: '#8B8B8B',
        borderRightColor: '#8B8B8B',
        borderWidth: 1,
    }
});
export default TrackInfo;