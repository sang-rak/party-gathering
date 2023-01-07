import { useState } from 'react';
import { ILogin, ITokens } from 'types/Login.type';
import { handlePostLogin } from 'libs/apis/login.api';

const useLogin = () => {
  const [info, setInfo] = useState<{ id: string, password: string }>({
    id: '',
    password: '',
  });



  return {
    info,
    setInfo,

  };
};

export default useLogin;
