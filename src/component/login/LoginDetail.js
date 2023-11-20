import { useDispatch, useSelector } from "react-redux"
import { Avatar, Tabs, Typography } from 'antd'
import { UserOutlined, FileProtectOutlined, LogoutOutlined, BellOutlined } from '@ant-design/icons'
import { useState } from "react";
import { MDBBtn, MDBCol, MDBInput, MDBRow } from "mdb-react-ui-kit";
import moment from 'moment';
import { useNavigate } from "react-router-dom";
import { logoutUser } from "../../features/user/userThunk";
const { Title } = Typography;
const { TabPane } = Tabs;
const LoginDetail = () => {
    const navigate = useNavigate()
    const userDetail = JSON.parse(localStorage.getItem('userDetail'));
    const namelocal = userDetail?.data.name;
    const [name, setname] = useState('')
    const [birthday, setbirthday] = useState(856345)
    const [gender, setgender] = useState(true)
    const [address, setaddress] = useState('')
    const [phoneNumber, setphoneNumber] = useState(0)
    const [email, setemail] = useState('')
    const [username, setusername] = useState('')
    const [password, setpassword] = useState('')
    const [identificationNumber, setidentificationNumber] = useState('')
    const handleDateChangestart = (dates) => {
        setbirthday(moment(dates).valueOf());
    };
    const formData = {
        name: name,
        gender: gender,
        address: address,
        phoneNumber: phoneNumber,
        email: email,
        identificationNumber: identificationNumber,
        point: 9
    }
    const dispatch = useDispatch()
    const logout = () => {
        dispatch(logoutUser())
        navigate('/login')
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        if (password.length < 8 || username.length < 8) {
            alert('Mật khẩu và tài khoản phải có ít nhất 8 ký tự');
            return;
        }
        else if (phoneNumber.toString().length !== 10) {
            alert('Số điện thoại phải có đúng 10 số');
            return;
        }
        else {
            fetch('http://localhost:8080/api/v1/login/registers', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            })
                .then(response => response.json())
                .then(data => {
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    };
    const [activeTab, setActiveTab] = useState('1');
    const items = [
        {
            key: '1',
            label: <div style={{ display: 'flex' }}><BellOutlined style={{ fontSize: 25 }} /><Title level={5} style={{ marginTop: 2 }}>Thông báo email</Title></div>,
            children: <div style={{ width: '100%', height: 200, border: '1px solid #b0b0b0', borderRadius: 8, padding: '10px 20px 10px 20px', backgroundColor: 'rgba(255,255,255,1.00)' }}>
                <Title level={4}>Thông báo email</Title>
                <div>Tùy chọn email:chúng tôi sẽ gửi các thông báo về thanh toán,đổi mật khẩu quên mật khẩu vào email: <Title level={5}>{userDetail?.data.email}</Title></div>
            </div>,
        },
        {
            key: '2',
            label: <div style={{ display: 'flex' }}><UserOutlined style={{ fontSize: 25 }} /><Title level={5} style={{ marginTop: 2 }}>Quản lý tài khoản</Title></div>,
            children: <div>
                <div><Title level={4}>Thông tin tài khoản</Title></div>
                <div style={{ width: '100%', height: 500, border: '1px solid #b0b0b0', borderRadius: 8, padding: '10px 20px 10px 20px', backgroundColor: 'rgba(255,255,255,1.00)' }}>
                    <div><Title level={4}>Dữ liệu cá nhân</Title></div>
                    <form onSubmit={handleSubmit}>
                        <MDBRow>
                            <MDBCol col='6'>
                                <MDBInput
                                    wrapperClass='mb-4'
                                    label='Họ và Tên'
                                    id='name'
                                    type='text'
                                    onChange={(e) => setname(e.target.value)}
                                    defaultValue={namelocal}
                                    required
                                />
                            </MDBCol>
                            <MDBCol col='6'>
                                <MDBInput
                                    wrapperClass='mb-4'
                                    label='Địa chỉ'
                                    id='address'
                                    type='text'
                                    required
                                    defaultValue={userDetail?.data.address}
                                    onChange={(e) => setaddress(e.target.value)}
                                />
                            </MDBCol>
                        </MDBRow>
                        <MDBInput wrapperClass='mb-4' required label='Email' id='email' type='email' defaultValue={userDetail?.data.email}
                            onChange={(e) => setemail(e.target.value)} />
                        <MDBInput wrapperClass='mb-4' label='Số điện thoại' id='phoneNumber' type='number' required defaultValue={userDetail?.data.phoneNumber}
                            onChange={(e) => setphoneNumber(e.target.value)} />
                        <MDBInput wrapperClass='mb-4' label='Số căn cước công dân' id='identificationNumber' type='number' required defaultValue={userDetail?.data.identificationNumber}
                            onChange={(e) => setidentificationNumber(e.target.value)} />
                        <MDBRow>
                            <MDBCol col='6'>
                                Giới tính &emsp;&emsp;
                                <label>
                                    <input
                                        type="radio"
                                        name="gender"
                                        value={true}
                                        defaultChecked={true}
                                    />
                                    Nam
                                </label>
                                &emsp;
                                <label>
                                    <input
                                        type="radio"
                                        name="gender"
                                        value={false}
                                    />
                                    Nữ
                                </label>
                            </MDBCol>
                        </MDBRow>
                        <MDBBtn type="submit" className='w-100 mb-4' size='md' style={{ marginTop: 10 }}>Lưu</MDBBtn>
                    </form>
                </div>
            </div>,
        }, {
            key: '3',
            label: <div style={{ display: 'flex' }}><FileProtectOutlined style={{ fontSize: 25 }} /><Title level={5} style={{ marginTop: 2 }}>Điều khoản và chính sách</Title></div>,
            children: <div style={{ width: '100%', height: '100%', border: '1px solid #b0b0b0', borderRadius: 8, padding: '10px 20px 10px 20px', backgroundColor: 'rgba(255,255,255,1.00)' }}>
                <Title level={3}>Điều khoản và Chính sách</Title>
                <Title level={4}>1. Điều khoản sử dụng</Title>
                <div>
                    1.1 Điều khoản tổng quan
                    Chào mừng bạn đến với trang web thuê homestay của chúng tôi. Việc sử dụng trang web này phải tuân theo các điều khoản và điều kiện sau đây. Bằng cách sử dụng trang web, bạn đồng ý và chấp nhận các điều khoản này mà không có bất kỳ giới hạn hay điều kiện nào.
                    <br />
                    1.2 Điều khoản đặt phòng
                    Khi bạn thực hiện đặt phòng trên trang web, bạn cam kết rằng tất cả thông tin bạn cung cấp là chính xác và đầy đủ. Chúng tôi có quyền từ chối đặt phòng hoặc hủy bỏ bất kỳ đặt phòng nào nếu chúng tôi phát hiện thông tin không chính xác hoặc gian lận.
                </div>
                <Title level={4}>2. Chính sách bảo mật</Title>
                <div>
                    2.1 Thu thập thông tin
                    Chúng tôi thu thập thông tin cá nhân từ bạn chỉ khi bạn cung cấp thông tin đó một cách tự nguyện thông qua quá trình đặt phòng hoặc tạo tài khoản. Chúng tôi cam kết bảo vệ thông tin cá nhân của bạn và không chia sẻ thông tin này với bất kỳ bên thứ ba nào mà không có sự đồng ý của bạn.
                    <br />
                    2.2 An toàn thông tin
                    Chúng tôi sử dụng biện pháp bảo mật công nghệ để bảo vệ thông tin cá nhân của bạn. Tuy nhiên, chúng tôi không thể đảm bảo an ninh tuyệt đối và không chịu trách nhiệm cho bất kỳ mất mát thông tin nào do sự không cố ý hoặc truy cập trái phép.
                </div>
                <Title level={4}>3. Chính sách hủy và hoàn tiền</Title>
                <div>
                    3.1 Hủy đặt phòng
                    Chính sách hủy đặt phòng được áp dụng theo từng homestay cụ thể và sẽ được hiển thị rõ trước khi bạn hoàn tất quá trình đặt phòng. Hãy kiểm tra và hiểu rõ chính sách hủy trước khi thực hiện đặt phòng. <br />
                    3.2 Hoàn tiền
                    Quy định về hoàn tiền cũng phụ thuộc vào chính sách của homestay cụ thể và sẽ được thông báo trong quá trình đặt phòng. Hãy đảm bảo bạn đã đọc và hiểu rõ các điều khoản về hoàn tiền.
                </div>
            </div >,
        },
        {
            key: '4',
            label: <div style={{ display: 'flex' }}><LogoutOutlined style={{ fontSize: 25 }} /><Title level={5} style={{ marginTop: 2 }}>Đăng xuất</Title></div>,
            children: <button type="button" className="btn btn-primary" style={{ color: 'black' }} onClick={logout}>
                <UserOutlined /> Đăng xuất
            </button>,
        }
    ];
    const handleTabChange = (key) => {
        setActiveTab(key);
    };
    const [tabPosition, setTabPosition] = useState('left');
    const activeItem = items.find(item => item.key === activeTab);
    return (
        <section style={{ padding: '50px 270px 270px 270px', display: 'flex' }}>
            <div style={{ width: '30%', height: 400, border: '1px solid #b0b0b0', borderRadius: 8, padding: '10px 0px 10px 0px', backgroundColor: 'rgba(255,255,255,1.00)' }}>
                <div style={{ display: "flex", padding: '0px 15px 0px 20px' }}>
                    <Avatar size={64} icon={<UserOutlined />} />
                    <Title level={4} style={{ marginLeft: 20, marginTop: 20 }}>{namelocal}</Title>
                </div>
                <hr />
                <div style={{ display: "flex", padding: '0px 0px 0px 20px' }}>
                    <Tabs
                        tabPosition={tabPosition}
                        activeKey={activeTab}
                        onChange={handleTabChange}
                    >
                        {items.map(item => (
                            <TabPane tab={item.label} key={item.key}>
                                {item.content}
                            </TabPane>
                        ))}
                    </Tabs>
                </div>
            </div>
            <div style={{ marginTop: '20px', marginLeft: 50, width: '100%' }}>
                <Title level={4}>Cài đặt</Title>
                {activeItem?.children}
            </div>
        </section>
    )
}
export default LoginDetail
