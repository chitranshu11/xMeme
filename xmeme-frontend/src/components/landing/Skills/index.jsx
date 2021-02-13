
import React, { useContext, useEffect, useState } from "react";
import { ThemeContext } from "providers/ThemeProvider";
import { Container, Card } from "components/common";

import axios from "axios";

import { Wrapper, Grid, Item, Content, Stats } from "./styles";

export const Skills = () => {
  const { theme } = useContext(ThemeContext);
  
  const [memes, setMemes] = useState([])
  useEffect(async () => {

    const result = await axios(
      'https://xmeme-chitranshu-gour.herokuapp.com/memes'
    );
    const memes_list = result.data;
    setMemes(memes_list);
  }, []);
  return (
    <Wrapper as={Container} id="skills">
      {memes.length > 0 && <h2>Meme Feed</h2>}
      <Grid>
        {memes.map((node) => (
          <Item
            key={node.name}
            as="a"
            theme={theme}
          >
            <Card theme={theme}>
              <Content>
                <h3>{node.name}</h3>
                <p>{node.caption}</p>
                <img src={node.url} alt={node.url} />
              </Content>
              <Stats theme={theme}>
              
              </Stats>
            </Card>
          </Item>
        ))}
      </Grid>
    </Wrapper>
  );
};
