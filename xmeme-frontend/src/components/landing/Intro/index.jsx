
import React, { useContext } from 'react';
import { ThemeContext } from 'providers/ThemeProvider';
import { Header } from 'components/theme';



export const Intro = () => {
  const { theme } = useContext(ThemeContext);

  return (
    <div>
      <Header />
    </div>
  );
};
