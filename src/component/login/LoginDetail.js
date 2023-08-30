import { useSelector } from "react-redux"
import { Typography } from 'antd'

const { Title } = Typography;

const LoginDetail = () => {
    const getdata = useSelector((state) => state.user.userData)
    return (
        <>
            {getdata.fullname}
            <Title level={4}>Danh mục</Title>
        </>
    )
}
export default LoginDetail