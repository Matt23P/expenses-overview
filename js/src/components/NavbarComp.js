import React from 'react'
import { Navbar, NavbarBrand, NavbarContent, NavbarItem, Link } from "@nextui-org/react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins } from '@fortawesome/free-solid-svg-icons';
import { EoUser } from '../utils/EoUser';

function NavbarComp() {
    return (
        <>
            <Navbar shouldHideOnScroll isBordered className='bg-gray-300 flex justify-center' style={{ boxShadow: "3px 3px 30px 5px rgba(62,66,66,0.35)" }}>
                <NavbarContent className="hidden sm:flex gap-4" justify="center">
                    <NavbarBrand className='flex justify-center items-cente' justify="center">
                        <Link href="/" color='foreground' className='font-semibold text-4xl text-gray-800'>
                            <FontAwesomeIcon icon={faCoins} className='mr-3' />
                            Expenses Overview
                        </Link>
                    </NavbarBrand>
                </NavbarContent>
                {EoUser.isLogged ? (
                    <NavbarContent justify="end">
                        <NavbarItem className="hidden lg:flex gap-4">
                            <Link href="/expenses" color='foreground' className='text-lg'>Expenses</Link>
                        </NavbarItem>
                        <NavbarItem className="hidden lg:flex gap-4">
                            <Link href="/analytics" color='foreground' className='text-lg'>Analytics</Link>
                        </NavbarItem>
                        <NavbarItem>
                            <Link href="/" color='foreground' className='text-lg' onClick={() => { EoUser.logout() }}>Logout</Link>
                        </NavbarItem>
                    </NavbarContent>
                ) : (
                    <NavbarContent justify="end">
                        <NavbarItem className="hidden lg:flex gap-4">
                            <Link href="/login" color='foreground' className='text-lg'>Login</Link>
                        </NavbarItem>
                        <NavbarItem>
                            <Link href="/signup" color='foreground' className='text-lg'>Sign Up</Link>
                        </NavbarItem>
                    </NavbarContent>
                )}
            </Navbar>
        </>
    );
}

export default NavbarComp;