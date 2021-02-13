import React from 'react';
import axios from 'axios';
import { Formik, Form, FastField, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { Button, Input } from 'components/common';
import { Error, Center, InputField, Wrapper } from './styles';

export default () => (
  <Wrapper>
    <h2>Post Meme</h2>
    <Formik
      initialValues={{
        name: '',
        caption: '',
        url: '',
        success: false,
      }}
      validationSchema={Yup.object().shape({
        name: Yup.string().required('Name field is required'),
        caption: Yup.string()
          .required('Caption field is required'),
        url: Yup.string().url("url required")
        .required('Url field is required'),
      })}
      onSubmit={async ({ name, caption, url }, { setSubmitting, resetForm, setFieldValue }) => {
        try {
          await axios({
            method: 'POST',
            url: 'https://xmeme-chitranshu-gour.herokuapp.com/memes',
            headers: {
              'Content-Type': 'application/json',
            },
            data: JSON.stringify({
              name,
              caption,
              url,
            }),
          });
          setSubmitting(false);
          setFieldValue('success', true);
          setTimeout(() => resetForm(), 6000);
          window.location.reload();

        } catch (err) {
          setSubmitting(false);
          setFieldValue('success', false);
          alert("Something went wrong, please try again!"); // eslint-disable-line
        }
      }}
    >
      {({ values, touched, errors, setFieldValue, isSubmitting }) => (
        <Form>
          <InputField>
            <Input
              as={FastField}
              type="text"
              name="name"
              component="input"
              aria-label="name"
              placeholder="Enter Fullname*"
              error={touched.name && errors.name}
            />
            <ErrorMessage component={Error} name="name" />
          </InputField>
          <InputField>
            <Input
              id="email"
              aria-label="caption"
              component="input"
              as={FastField}
              type="text"
              name="caption"
              placeholder="Enter Caption*"
              error={touched.caption && errors.caption}
            />
            <ErrorMessage component={Error} name="caption" />
          </InputField>
          <InputField>
            <Input
              as={FastField}
              component="textarea"
              aria-label="url"
              id="message"
              type="text"
              name="url"
              placeholder="Enter Image URL*"
              error={touched.url && errors.url}
            />
            <ErrorMessage component={Error} name="url" />
          </InputField>
          {values.success && (
            <InputField>
              <Center>
                <h4>Your meme has been posted.</h4>
              </Center>
            </InputField>
          )}
          <Center>
            <Button secondary type="submit" disabled={isSubmitting} className="submit-btn">
              Submit
            </Button>
          </Center>
        </Form>
      )}
    </Formik>
  </Wrapper>
);
