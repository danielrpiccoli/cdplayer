import React, { useState } from 'react';
import * as Font from 'expo-font';
import PlayerScreen from './src/screens/playerScreen';

Font.loadAsync({
    'ms-sans-serif': require ('./assets/fonts/micross.ttf'),
  });

export default function App() {
  return <PlayerScreen />
}