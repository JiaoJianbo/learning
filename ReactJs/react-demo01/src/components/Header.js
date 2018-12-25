import React, { Component } from 'react';
import PropTypes from 'prop-types';

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            title: ''
         };
    }

    render() {
        return (
            <div>
                <h3>{this.props.title}</h3>
            </div>
        );
    }
}

// defaultProps 指定默认值
Header.defaultProps = {
    title: 'Please provide a header'
};

/* PropTypes 验证父组件的传值
 * https://reactjs.org/docs/typechecking-with-proptypes.html
 */
Header.propTypes = {
    title: PropTypes.string
};

export default Header;