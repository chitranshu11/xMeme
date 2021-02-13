import React from 'react';
import { Container } from 'components/common';
import meme from 'assets/illustrations/meme.png';
import { Wrapper, Details, Thumbnail } from './styles';
import ContactForm from './ContactForm';

export const Contact = () => (
  <Wrapper as={Container} id="contact">
    <Details>
      <ContactForm />
    </Details>
    <Thumbnail>
      <img src={meme} alt="Meme Wala" />
    </Thumbnail>
  </Wrapper>
);
