import { useSelector } from "react-redux"
import { Avatar, Tabs, Typography } from 'antd'
import { UserOutlined, DollarOutlined } from '@ant-design/icons'
import { useState } from "react";

const { Title } = Typography;
const { TabPane } = Tabs;
const LoginDetail = () => {
    const [activeTab, setActiveTab] = useState('1');
    const items = [
        {
            key: '1',
            label: <div style={{ display: 'flex' }}><DollarOutlined style={{ fontSize: 25 }} /><Title level={5} style={{ marginTop: 2 }}>Điểm thưởng của tôi</Title></div>,
            children: 'Content of Tab Pane 1',
        },
        {
            key: '2',
            label: 'Tab 2',
            children: 'Content of Tab Pane 2',
        },
        {
            key: '3',
            label: 'Tab 3',
            children: 'Content of Tab Pane 3',
        },
    ];
    const handleTabChange = (key) => {
        setActiveTab(key);
    };
    const [tabPosition, setTabPosition] = useState('left');
    const userDetail = JSON.parse(localStorage.getItem('userDetail'));
    const name = userDetail?.data.name;
    return (
        <section style={{ padding: '50px 270px 270px 270px' }}>
            <div style={{ width: '30%', height: 500, border: '1px solid #b0b0b0', borderRadius: 8, padding: '10px 0px 10px 0px' }}>
                <div style={{ display: "flex", padding: '0px 15px 0px 20px' }}>
                    <Avatar size={64} icon={<UserOutlined />} />
                    <Title level={4} style={{ marginLeft: 20, marginTop: 20 }}>{name}</Title>
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
            <div style={{ marginTop: '20px' }}>
                <Title level={4}>Nội dung</Title>
                {items.find(item => item.key === activeTab)?.content}
            </div>
        </section>
    )
}
export default LoginDetail
